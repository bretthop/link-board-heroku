// TODO: Make file paths relative
var webapp_root = 'C:\\Dev\\Projects\\Javascript\\HelloNode\\webapp';

var connect = require('C:\\Users\\brett\\node_modules\\connect'),
    securityFilter = require('./filters/security-filter.js'),
    usersEndpoint = require('./endpoints/users-endpoint.js'),
    linkGroupsEndpoint = require('./endpoints/link-groups-endpoint.js'),
    linksEndpoint = require('./endpoints/links-endpoint.js'),
    urlUtils = require('./utils/url-utils');

connect()
    .use(urlUtils.parseParams())
    .use(connect.json())// Parses incoming JSON requests and adds it to the request object as 'req.body'
    .use('/api', securityFilter.process('/api/users', 'POST'))
    .use('/api/users', usersEndpoint.process)
    .use('/api/linkGroups', linkGroupsEndpoint.process)
    .use('/api/links', linksEndpoint.process)
    .use(connect.static(webapp_root))
    .listen(4444);