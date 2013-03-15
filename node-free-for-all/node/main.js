// TODO: Make file paths relative
var webapp_root = 'C:\\Dev\\Projects\\Javascript\\HelloNode\\webapp';

var connect = require('C:\\Users\\brett\\node_modules\\connect'),
    UsersEndpoint = require('./endpoints/UsersEndpoint.js');

connect()
    .use('/api/users', UsersEndpoint.process)
    .use(connect.static(webapp_root))
    .listen(4444);