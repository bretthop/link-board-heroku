class LinkGroupsController < ApplicationController
  def index
    groups = LinkGroup.find_all_by_user_account_id @current_user.id

    render :json => groups
  end

  def create
    @new_group = LinkGroup.new({
        :user_account_id => @current_user.id,
        :title => params[:title],
        :description => params[:description]
    })

    @new_group.save

    render :json => @new_group
  end
end
