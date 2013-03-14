class UsersController < ApplicationController
  skip_before_filter :require_login, :only => [:create]

  def index
    render :json => @current_user
  end

  def create
    new_user = UserAccount.new({
        :username => params[:username],
        :password => params[:password],
        :email => params[:email],
        :first_name => params[:firstName],
        :last_name => params[:lastName]
    })

    new_user.save

    render :json => new_user
  end
end
