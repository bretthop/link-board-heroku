class UsersController < ApplicationController
  skip_before_filter :require_login, :only => [:create]

  def index
    render :json => @current_user
  end

  def create
    attrs = {
        :username => params[:username],
        :password => params[:password],
        :email => params[:email],
        :first_name => params[:firstName],
        :last_name => params[:lastName]
    }

    new_user = UserAccount.create attrs

    render :json => new_user
  end
end
