// TODO: Make file paths relative
var webapp_root = 'C:\\Dev\\Projects\\Javascript\\HelloNode\\webapp';

var connect = require('C:\\Users\\brett\\node_modules\\connect'),
    securityFilter = require('./filters/security-filter.js');
    usersEndpoint = require('./endpoints/users-endpoint.js');

connect()
    .use('/api', securityFilter.process)
    .use('/api/users', usersEndpoint.process)
    .use(connect.static(webapp_root))
    .listen(4444);