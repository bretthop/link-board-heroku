function process(req, res)
{
    switch (req.method) {
        case 'GET':
            console.log('Processing users get');

            res.writeHead(200);
            res.end();

            break;
        case 'POST':
            console.log('processing users post');

            res.writeHead(200);
            res.end();

            break;
        default:
            console.log('unsupported method for usersEndpoint');

            res.writeHead(405);
            res.end();
    }
}

module.exports.process = process;