module.exports.validate = function(link)
{
    if (!link) { return 'Link is required.'; }
    if (!link.group || !link.group.id || link.group.id.match('^[0-9]+$') == null) { return 'Group is required.'; }
    if (!link.title || link.title.length < 1) { return 'Title is required.'; }
    if (!link.href || link.href.length < 1) { return 'Href is required.'; }

    return undefined;
};