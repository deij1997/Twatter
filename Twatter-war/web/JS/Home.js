
// Twat object
// .render()
var Twat = function(json, parent) {
  this.contents = json;
  this.parent = parent;
  
  this.render = function() {
    var div = document.createElement('DIV');
    div.innerHTML = this.contents.contents;
    
    this.parent.appendChild(div);
  };
};


function loadTwats () {
call("GET", "api/twats/cunt1/timeline/", null, function(response, success, code){
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
