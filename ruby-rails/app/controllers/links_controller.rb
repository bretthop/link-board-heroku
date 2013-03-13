class LinksController < ApplicationController
  def index
    links = Link.find_all_by_link_group_id params[:groupId]

    render :json => links
  end

  def create
    @new_link = Link.new({
         :link_group_id => params[:group][:id],
         :title => params[:title],
         :href => params[:href],
         :description => params[:description]
    })

    @new_link.save

    render :json => @new_link
    #render :json => params
  end
end
