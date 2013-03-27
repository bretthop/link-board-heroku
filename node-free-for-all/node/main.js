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
    if (req.url.length >= '/api'.length && req.url.substr(0, '/api'.length) == '/api') {
        parseJson.fromRequestBody(req, res, function() {
            urlUtils.parseParams()(req, res, function() {
                securityFilter.process('/api/users', 'POST')(req, res, function() {
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
                });
            });
        });
    }
    else { // Serve static files
        send(req, url.parse(req.url).pathname)
            .root(webapp_root)
            .pipe(res);
    }
}).listen(3000);