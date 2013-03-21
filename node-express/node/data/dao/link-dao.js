var baseDao = require('./base-dao.js');

module.exports.findById = function(id, callback)
{
    var qry = 'SELECT * FROM link WHERE id = $1';
    var params = [id];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result.rows[0]);
            }
        }
    });
};

module.exports.findAllByGroupId = function(groupId, callback)
{
    var qry = 'SELECT * FROM link WHERE link_group_id = $1';
    var params = [groupId];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result.rows);
            }
        }
    });
};

module.exports.save = function(link, callback)
{
    var qry = 'INSERT INTO link (link_group_id, title, href, description) VALUES ($1, $2, $3, $4)';
    var params = [link.group.id, link.title, link.href, link.description];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result);
            }
        }
    });
};

module.exports.delete = function(linkId, callback)
{
    var qry = 'DELETE FROM link WHERE id = $1';
    var params = [linkId];

    baseDao.executeQuery(qry, params, function() {
        if (callback) {
            callback();
        }
    });
};