module.exports.validate = function(res, linkGroup)
{
    var err = undefined;

    if (!linkGroup) { err = 'Link Group is required.'; }
    else if (!linkGroup.title || linkGroup.title.length < 1) { err = 'Title is required.'; }

    if (err) {
        res.send(400, err);
    }

    return err == undefined;
};