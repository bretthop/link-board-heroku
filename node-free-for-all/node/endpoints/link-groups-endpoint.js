function process(req, res)
{
    switch (req.method) {
        case 'GET':
            console.log('Processing link groups get');

            res.writeHead(200);
            res.end();

            break;
        case 'POST':
            console.log('processing link groups post');

            res.writeHead(200);
            res.end();

            break;
        default:
            console.log('unsupported method for link groups');

            res.writeHead(405);
            res.end();
    }
}

module.exports.process = process;