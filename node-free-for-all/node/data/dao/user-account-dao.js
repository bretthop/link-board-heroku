var pg = require('C:\\Users\\brett\\node_modules\\pg');
var conString = 'postgres://postgres:password@localhost:5432/linkboard';

// TODO: Refactor common code, add error handling, add mapping to 'bean' (i.e. the prop first_name => firstName)

module.exports.findByUsernameAndPassword = function (username, password, callback)
{
    var client = new pg.Client(conString);

    client.connect(function(err, client) {
        client.query('SELECT * FROM user_account WHERE username = $1 AND password = $2',
            [username, password],
            function(err, result) {
                if (result) {
                    if (callback) {
                        callback(result.rows[0]);
                    }
                }
            });
    });
};

module.exports.save = function (user, callback)
{
    var client = new pg.Client(conString);

    client.connect(function(err, client, done) {
        client.query('INSERT INTO user_account (username, password, email, first_name, last_name) VALUES ($1, $2, $3, $4, $5)',
            [user.username, user.password, user.email, user.firstName, user.lastName],
            function(err, result) {
                if (callback) { callback(result); }
            });
    });
};