class UserAccount < ActiveRecord::Base
  attr_accessible :username, :password, :email, :first_name, :last_name

  def self.create(attrs)
    model = UserAccount.new attrs

    model.save

    return model
  end
end
