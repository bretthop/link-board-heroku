module LinkGroupsHelper
  def validate(group)
    return 'Link Group must be provided.' unless group
    return 'User must be provided.' unless group[:user_account_id] && group[:user_account_id].to_s.size() >= 1
    return 'User Id must be a valid number.' unless group[:user_account_id].to_s.match('^[0-9]+$')
    return 'Title must be provided.' unless group[:title] && group[:title].size >= 1

    nil
  end
end
