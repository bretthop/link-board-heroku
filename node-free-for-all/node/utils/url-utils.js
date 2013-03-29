var url = require('url');


module.exports.parseParams = function(req, res, next)
{
    var url_parts = url.parse(req.url, true);
    req.query = url_parts.query;

    next();
};