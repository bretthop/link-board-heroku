var pg = require('C:\\Users\\brett\\node_modules\\pg');

module.exports.executeQuery = function(qry, params, callback)
{
    var client = new pg.Client(process.env.DATABASE_URL);

    client.connect(function(err, client) {
        client.query(qry, params, function(err, result) {
            if (err) {
                // TODO: Handle this better; atm this will terminate the node process and leave the client with a closed connection and no error code
                throw err;
            }

            if (callback) {
                callback(result);
            }
        });
    });
};