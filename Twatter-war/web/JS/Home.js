
function loadTwats () {
call("GET", "api/cunt2/timeline/", null, function(response, success, code){
    if (success) {
        var holder = document.getElementById('twat-holder');
        var objectlijst = JSON.parse(response);
        for (var i = 0; i < objectlijst.length; i++)
        {
            var twat = new Twat(objectlijst[i], holder);
            
            twat.render();
        }
    }
});
}

// Twat object
// .render()
var Twat = function(json, parent) {
  this.contents = json;
  this.parent = parent;
  
  this.render = function() {
    var div = document.createElement('DIV');
    div.innerHTML = this.contents.contents; // change to your tweet contents
    
    // add more if you want to
    // IMG for img, etc....
    
    this.parent.appendChild(div);
  }
}

