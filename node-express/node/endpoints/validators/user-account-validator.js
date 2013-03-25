module.exports.validate = function(res, userAccount)
{
    var err = undefined;

    if (!userAccount) { err = 'User is required.'; }
    else if (!userAccount.username || userAccount.username.length < 1) { err = 'Username is required.'; }
    else if (!userAccount.password || userAccount.password.length < 1) { err = 'Password is required.'; }

    if (err) {
        res.send(400, err);
    }

    return err == undefined;
};