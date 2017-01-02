var sketchesGenereated = 0;

var me = 'sketchybot';

console.log("The Bot is Starting");

var Twit = require('twit');
var T = new Twit(require('./config'));



console.log("Opening Stream to Twitter...");
var stream = T.stream('user');
stream.on('tweet',tweetEvent);
console.log("Opened");

function tweetEvent(eventMsg){
  console.log("An event has occured");
  var tweetedAtThisUser = eventMsg.in_reply_to_screen_name;
  var from = eventMsg.user.screen_name;

  if (tweetedAtThisUser == me){
    tweetRandomSketchAt(from);
    ++sketchesGenereated;
  }
}


function tweetRandomSketchAt(user){
  console.log("Starting Generation...");
  var generateSketchCmd = 'randomSketch/randomSketch';

  var exec = require ('child_process').exec;
  exec(generateSketchCmd,processing);

  var fs = require('fs');

  function processing(){
    console.log("Generating and Tweeting Sketch...");
    var fileName = 'randomSketch/output.png';
    var params = {
      encoding: 'base64'
    };

    var sketchInBase64 = fs.readFileSync(fileName,params);

    T.post('media/upload',{media_data: sketchInBase64}, uploaded);

    function uploaded(err,data,response){
      var id = data.media_id_string;
      var tweet = {
        status: ('.@'+user+', here you go :)'),
        media_ids: [id]
      };

      T.post('statuses/update',tweet, tweeted);
    }

    function tweeted(err, data, response){
        if (err) {
          console.log("Tweet failed");
          console.log(err.message);
        } else {
          console.log("Tweeted");
        }
      }
    }
  }
