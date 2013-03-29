var webapp_root = __dirname + '\\..\\webapp';

var http = require('http'),
    send = require('send'),
    url = require('url'),
    securityFilter = require('./filters/security-filter.js'),
    usersEndpoint = require('./endpoints/users-endpoint.js'),
    linkGroupsEndpoint = require('./endpoints/link-groups-endpoint.js'),
    linksEndpoint = require('./endpoints/links-endpoint.js'),
    urlUtils = require('./utils/url-utils'),
    parseJson = require('./utils/parse-json.js');

http.createServer(function(req, res) {
    if (isApiCall(req)) {
        parseJson.fromRequestBody(req, res, function() {
            urlUtils.parseParams(req, res, function() {
                securityFilter.process('/api/users', 'POST', req, res, function() {
                    handleApiCall(req, res);
                });
            });
        });
    }
    else {
        handleStatic(req, res);
    }
}).listen(3000);

function handleStatic(req, res)
{
    send(req, url.parse(req.url).pathname)
        .root(webapp_root)
        .pipe(res);
}

function isApiCall(req)
{
    var apiPrefix = '/api';

    return req.url.length >= apiPrefix.length && req.url.substr(0, apiPrefix.length) == apiPrefix;
}

function handleApiCall(req, res)
{
    switch (req.url.split('?')[0]) {
        case '/api/users':
            usersEndpoint.process(req, res);
            break;
        case '/api/links':
            linksEndpoint.process(req, res);
            break;
        case '/api/linkGroups':
            linkGroupsEndpoint.process(req, res);
            break;
    }
}