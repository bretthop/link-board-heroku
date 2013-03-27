exports.fromRequestBody = function(req, res, next)
{
    req.on('data', function(chunk) {
        req.body = JSON.parse(chunk.toString());
    });

    req.on('end', next);
};