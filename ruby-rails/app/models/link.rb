class Link < ActiveRecord::Base
  attr_accessible :link_group_id, :title, :href, :description

  def self.create_if_allowed(user_id, attrs)
    model = Link.new attrs

    group = LinkGroup.where :id => model.link_group_id, :user_account_id => user_id

    if group.size() > 0
      model.save

      return model
    else
      return nil
    end
  end

  def self.find_by_link_group_if_allowed(user_id, group_id)
    group = LinkGroup.where :id => group_id, :user_account_id => user_id

    if group.size() > 0
      return Link.find_all_by_link_group_id group_id
    else
      return nil
    end
  end

  def self.delete_if_allowed(user_id, link_id)
    link = Link.find link_id

    group = LinkGroup.where :id => link.link_group_id, :user_account_id => user_id

    if group.size() > 0
      link.delete

      return true
    else
      return false
    end
  end
end
