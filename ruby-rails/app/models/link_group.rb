class LinkGroup < ActiveRecord::Base
  attr_accessible :title, :description, :user_account_id

  def self.create(attrs)
    model = LinkGroup.new attrs

    model.save

    return model
  end
end
