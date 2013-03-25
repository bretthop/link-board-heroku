var userEndpoint = require('./endpoints/user.js'),
    linkGroupEndpoint = require('./endpoints/linkGroup.js'),
    linkEndpoint = require('./endpoints/link.js'),
    securityFilter = require('./security/security-filter.js'),
    express = require('express'),
    app = express();

// Note: express.basicAuth has not been used because it does not (as far as i know) allow
//       you to exclude certain URLs (i.e. i want to match /api/* EXCEPT a POST to /api/users),
//       also it responds with a WWW-Authenticate header which is not what is desired

app .use(express.bodyParser())
    .use('/api', securityFilter.process('/api/users', 'POST')) // Note: See above
    .use('/api/users', userEndpoint.create)
    .use('/api/links', linkEndpoint.create)
    .use('/api/linkGroups', linkGroupEndpoint.create)
    .use(express.static(__dirname + '\\..\\webapp'))
    .listen(3000);