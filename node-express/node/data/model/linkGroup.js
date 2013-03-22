var linkGroupDao = require('../dao/link-group-dao.js');

exports.findByUser = function(userId)
{
    return linkGroupDao.findAllByUserId(userId);
};