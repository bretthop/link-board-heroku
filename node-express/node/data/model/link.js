var linkDao = require('../dao/link-dao.js');

exports.findById = function(id, callback) {
    linkDao.findById(id, function(result) {
        callback(result);
    });
};

exports.findByGroup = function(groupId, callback)
{
    linkDao.findAllByGroupId(groupId, function(result) {
        callback(result);
    });
};

exports.save = function(data, callback)
{
    linkDao.save(data, function(result) {
        callback(result);
    });
};

exports.delete = function(id, callback)
{
    linkDao.delete(id, function(result) {
        callback(result);
    });
};