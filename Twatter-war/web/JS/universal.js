function call(type, url, data, callback, h) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4) {
            if (xmlHttp.status >= 200 && xmlHttp.status < 300 || xmlHttp.status === 302) {
                callback(xmlHttp.responseText, true);
            } else if (xmlHttp.status >= 500 && xmlHttp.status < 600) {
                callback('INTERNAL ERROR: ' + xmlHttp.responseText, false, 1);
            } else if (xmlHttp.status >= 400 && xmlHttp.status < 500) {
                callback('CLIENT ERROR: ' + xmlHttp.responseText, false, 2);
            } else {
                callback(xmlHttp.responseText, false, 4);
            }
        }
    }
    xmlHttp.open(type, url, true);
    if (h) {
        xmlHttp.setRequestHeader('Content-Type', h);
    }
    xmlHttp.withCredentials = true;
    xmlHttp.send(data);
}