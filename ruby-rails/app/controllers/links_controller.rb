class LinksController < ApplicationController
  def index
    links = Link.find_by_link_group_if_allowed @current_user.id, params[:groupId]

    if links
      render :json => links
    else
      response.status = 403

      render :text => 'You do not have permission to access this group.'
    end
  end

  def create
    attrs = {
        :link_group_id => params[:group][:id],
        :title => params[:title],
        :href => params[:href],
        :description => params[:description]
    }

    new_link = Link.create_if_allowed @current_user.id, attrs

    if new_link
      render :json => new_link
    else
      response.status = 403

      render :text => 'You do not have permission to access this group.'
    end
  end

  def destroy
    result = Link.delete_if_allowed @current_user.id, params[:id]

    if result
      render :text => 'Link deleted successfully'
    else
      response.status = 403

      render :text => 'You do not have permission to access this group.'
    end
  end
end
