class LinksController < ApplicationController
  include LinksHelper

  def index
    links = Link.find_by_link_group @current_user.id, params[:groupId]

    if links
      render :json => links
    else
      response.status = 409

      render :text => 'The link group that was contained in the request is not valid for the current logged in user.'
    end
  end

  def create
    attrs = {
        :link_group_id => params[:group][:id],
        :title => params[:title],
        :href => params[:href],
        :description => params[:description]
    }

    validation_err = validate attrs

    if validation_err
      response.status = 400

      render :text => validation_err
    else
      new_link = Link.create @current_user.id, attrs

      if new_link
        render :json => new_link
      else
        response.status = 409

        render :text => 'The link group that was contained in the request is not valid for the current logged in user.'
      end
    end
  end

  def destroy
    result = Link.delete_link @current_user.id, params[:id]

    if result
      render :text => 'Link deleted successfully'
    else
      response.status = 409

      render :text => 'The link group that was contained in the request is not valid for the current logged in user.'
    end
  end
end
