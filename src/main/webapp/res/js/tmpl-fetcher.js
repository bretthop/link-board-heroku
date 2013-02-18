// TODO: Maybe move these to an app config file
var LINK_TMPL_URL = '/templates/linkTmpl.html';

tmplCache = {};

function fetchTmpl(url, callback)
{
    if (tmplCache[url] != undefined) {
        callback(tmplCache[url]);

        return;
    }

    $.get(url, function(tmpl) {
        tmplCache[url] = tmpl;

        callback(tmpl);
    });
}