function process(req, res)
{
    switch (req.method) {
        case 'GET':
            console.log('Processing links get');

            res.writeHead(200);
            res.end();

            break;
        case 'POST':
            console.log('processing links post');

            res.writeHead(200);
            res.end();

            break;
        case 'DELETE':
            console.log('processing links delete');

            res.writeHead(200);
            res.end();

            break;
        default:
            console.log('unsupported method for links');

            res.writeHead(405);
            res.end();
    }
}

module.exports.process = process;