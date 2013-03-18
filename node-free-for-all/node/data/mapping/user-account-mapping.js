module.exports.fromEntity = function(entity)
{
    if (!entity) { return entity; }

    return {
        id: entity.id,
        username: entity.username,
        password: entity.password,
        email: entity.email,
        firstName: entity.first_name,
        lastName: entity.last_name
    };
};