class LinkGroupsController < ApplicationController
  def index
    groups = LinkGroup.find_all_by_user_account_id @current_user.id

    render :json => groups
  end

  def create
    attrs = {
        :user_account_id => @current_user.id,
        :title => params[:title],
        :description => params[:description]
    }

    new_group = LinkGroup.create attrs

    render :json => new_group
  end
end
