class Link < ActiveRecord::Base
  attr_accessible :link_group_id, :title, :href, :description
end
