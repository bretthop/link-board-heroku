var baseEndpoint = require('./base-endpoint.js');

function methodGet(req, res)
{
    console.log('Processing links get');

    res.writeHead(200);
    res.end();
}

function methodPost(req, res)
{
    console.log('processing links post');

    res.writeHead(200);
    res.end();
}

module.exports.process = baseEndpoint.createEndpoint({ methodGet: methodGet, methodPost: methodPost });