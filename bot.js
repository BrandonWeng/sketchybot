console.log("The Bot is Starting");

var Twit = require('twit');
var T = new Twit(require('./config'));

var exec = require ('child_process').exec;

// Need FileSync
var fs = require('fs');

function tweetRandomSketchAt(user){
  var generateSketchCmd = 'processing-java --sketch=\'pwd\'/randomSketch --run';
  exec(generateSketchCmd,processing);

  function processing(){
    var fileName = 'randomSketch/output.png';
    var params = {
      encoding: 'base64'
    };

    var sketchInBase64 = fs.readFileSync(fileName,params);

    T.post('media/upload',{media_data: sketchInBase64}, uploaded);

    function uploaded(err,data,response){
      var id = data.media_id_string;
      var tweet = {
        status: (user+', here is your Sketch'),
        media_ids: [id]
      };

      T.post('statuses/update',tweet, tweeted);
    }
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

tweetRandomSketchAt('test');
