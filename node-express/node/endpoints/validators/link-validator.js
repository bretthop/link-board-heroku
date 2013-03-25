module.exports.validate = function(res, link)
{
    var err = undefined;

    if (!link) { err = 'Link is required.'; }
    else if (!link.group || !link.group.id || link.group.id.match('^[0-9]+$') == null) { err = 'Group is required.'; }
    else if (!link.title || link.title.length < 1) { err = 'Title is required.'; }
    else if (!link.href || link.href.length < 1) { err = 'Href is required.'; }

    if (err) {
        res.send(400, err);
    }

    return err == undefined;
};