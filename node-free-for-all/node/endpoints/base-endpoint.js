module.exports.createEndpoint = function(methods)
{
    function notImplemented(res) {
        res.writeHead(405);
        res.end();
    }

    var methodGet = methods && methods.methodGet ? methods.methodGet : undefined,
        methodPost = methods && methods.methodPost ? methods.methodPost : undefined,
        methodPut = methods && methods.methodPut ? methods.methodPut : undefined,
        methodDelete = methods && methods.methodDelete ? methods.methodDelete : undefined,
        methodHead = methods && methods.methodHead ? methods.methodHead : undefined;

    return function(req, res)
    {
        switch(req.method) {
            case 'GET':
                methodGet ? methodGet(req, res) : notImplemented(res);

                break;
            case 'POST':
                methodPost ? methodPost(req, res) : notImplemented(res);

                break;
            case 'PUT':
                methodPut ? methodPut(req, res) : notImplemented(res);

                break;
            case 'DELETE':
                methodDelete ? methodDelete(req, res) : notImplemented(res);

                break;
            case 'HEAD':
                methodHead ? methodHead(req, res) : notImplemented(res);

                break;
            default:
                notImplemented(res);

                break;
        }
    }
};