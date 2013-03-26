var webapp_root = __dirname + '\\..\\webapp';

var connect = require('connect'),
    securityFilter = require('./filters/security-filter.js'),
    usersEndpoint = require('./endpoints/users-endpoint.js'),
    linkGroupsEndpoint = require('./endpoints/link-groups-endpoint.js'),
    linksEndpoint = require('./endpoints/links-endpoint.js'),
    urlUtils = require('./utils/url-utils');


var http = require('http'),
    send = require('send'),
    url = require('url');

http.createServer(function(req, res) {
    if (req.url.length >= '/api'.length && req.url.substr(0, '/api'.length) == '/api') {
        urlUtils.parseParams()(req, res, function() {
            securityFilter.process('/api/users', 'POST')(req, res, function() {
                switch (req.url) {
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
    }
    else { // Serve static files
        send(req, url.parse(req.url).pathname)
            .root(webapp_root)
            .pipe(res);
    }
}).listen(3000);

// TODO: This is being replaced by a vanilla node app (see the node-express backend for an implementation using a framework)
/*
connect()
    .use(urlUtils.parseParams())
    .use(connect.json())// Parses incoming JSON requests and adds it to the request object as 'req.body'
    .use('/api', securityFilter.process('/api/users', 'POST'))
    .use('/api/users', usersEndpoint.process)
    .use('/api/linkGroups', linkGroupsEndpoint.process)
    .use('/api/links', linksEndpoint.process)
    .use(connect.static(webapp_root))
    .listen(3000);
*/
