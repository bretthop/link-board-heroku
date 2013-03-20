module.exports.validate = function(userAccount)
{
    if (!userAccount) { return 'User is required.'; }
    if (!userAccount.username || userAccount.username.length < 1) { return 'Username is required.'; }
    if (!userAccount.password || userAccount.password.length < 1) { return 'Password is required.'; }

    return undefined;
};