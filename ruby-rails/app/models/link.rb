class Link < ActiveRecord::Base
  attr_accessible :link_group_id, :title, :href, :description

  def self.create(user_id, attrs)
    model = Link.new attrs

    Link.do_access_check user_id, model.link_group_id do
      model.save

      return model
    end
  end

  def self.find_by_link_group(user_id, group_id)
    Link.do_access_check user_id, group_id do
      return Link.find_all_by_link_group_id group_id
    end
  end

  def self.delete_link(user_id, link_id)
    link = Link.find link_id

    Link.do_access_check user_id, link.link_group_id do
      link.delete

      return true
    end
  end

  def self.do_access_check(user_id, group_id)
    group = LinkGroup.where :id => group_id, :user_account_id => user_id

    if group.size() > 0
      yield
    else
      return nil
    end
  end
end
