var userEndpoint = require('./endpoints/user.js'),
    linkGroupEndpoint = require('./endpoints/linkGroup.js'),
    linkEndpoint = require('./endpoints/link.js'),
    securityFilter = require('./endpoints/filters/security-filter.js'),
    express = require('express'),
    app = express();

app .use(express.bodyParser())
    .use('/api', securityFilter.process('/api/users', 'POST'))
    .use('/api/users', userEndpoint.create)
    .use('/api/links', linkEndpoint.create)
    .use('/api/linkGroups', linkGroupEndpoint.create)
    .use(express.static(__dirname + '\\..\\webapp'))
    .listen(3000);