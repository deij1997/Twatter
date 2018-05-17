var username = "Deij1997";

// Twat object
// .render()
var Twat = function(json, parent) {
    this.contents = json;
    this.parent = parent;

    this.render = function() {
        var div = document.createElement('DIV');
        var h3 = document.createElement('H3');
        var span1 = document.createElement('SPAN');
        var span2 = document.createElement('SPAN');
        var ptje = document.createElement('P');

        var user = this.contents.poster.username;
        var contents = this.contents.contents;
        var senddate = this.contents.sendDateLong;
        var title = this.contents.title;

        var newdate = new Date(senddate);

        h3.innerHTML = title;
        span1.innerHTML = user;
        span2.innerHTML = contents;
        ptje.innerHTML = newdate;

        div.appendChild(h3);
        div.appendChild(span1);
        div.appendChild(span2);
        div.appendChild(ptje);

        this.parent.appendChild(div);
    };

    this.renderTop = function() {
        var div = document.createElement('DIV');
        var h3 = document.createElement('H3');
        var span1 = document.createElement('SPAN');
        var span2 = document.createElement('SPAN');
        var ptje = document.createElement('P');

        var user = this.contents.poster.username;
        var contents = this.contents.contents;
        var senddate = this.contents.sendDateLong;
        var title = this.contents.title;

        var newdate = new Date(senddate);

        h3.innerHTML = title;
        span1.innerHTML = user;
        span2.innerHTML = contents;
        ptje.innerHTML = newdate;

        div.appendChild(h3);
        div.appendChild(span1);
        div.appendChild(span2);
        div.appendChild(ptje);

        this.parent.prepend(div);
    };
};


function loadTwats() {
    call("GET", "api/twats/" + username + "/timeline/", null, function(response, success, code) {
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

function postTwat() {
    var titletext = document.getElementById('titlebox').value;
    var contentstext = document.getElementById('contentsbox').value;

    var data = 'title=' + titletext + '&contents=' + contentstext;

    call("POST", "api/twats/" + username + "/twat/", data, function(response, succes, code) {
        if (succes) {
            var holder = document.getElementById('twat-holder');
            var t = {
                'poster': {}
            };
            t.poster.username = username;
            t.contents = contentstext;
            t.title = titletext;
            t.sendDateLong = new Date().getTime();

            var twat = new Twat(t, holder);

            if (titletext == "")
            {
                //ToDo show message saying its empty
                alert("Enter a Title for your Twat!");
            }
            else if (contentstext == "")
            {
                alert("Write some contents for your Twat!")
            }
            else
            {
                twat.renderTop();
            }
        }
    }, 'application/x-www-form-urlencoded');
}