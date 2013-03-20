module.exports.validate = function(linkGroup)
{
    if (!linkGroup) { return 'Link Group is required.'; }
    if (!linkGroup.title || linkGroup.title.length < 1) { return 'Title is required.'; }

    return undefined;
};