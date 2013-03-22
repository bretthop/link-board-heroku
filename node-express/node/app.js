var express = require('express'), app = express();

var userEndpoint = require('./endpoints/user.js'),
    linkGroupEndpoint = require('./endpoints/linkGroup.js'),
    linkEndpoint = require('./endpoints/link.js'),
    securityFilter = require('./endpoints/filters/security-filter.js');

userEndpoint.init('/api/users', app);
linkGroupEndpoint.init('/api/linkGroups', app);
linkEndpoint.init('/api/links', app);

app.use('/api', securityFilter.process('/api/users', 'POST'));
app.use(express.static(__dirname + '\\..\\webapp'));

app.listen(3000);