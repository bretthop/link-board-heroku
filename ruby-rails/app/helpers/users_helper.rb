module UsersHelper
  def validate(user)
    return 'User must be provided.' unless user
    return 'Username must be provided.' unless user[:username] && user[:username].size >= 1
    return 'Password must be provided.' unless user[:password] && user[:password].size >= 1

    nil
  end
end
