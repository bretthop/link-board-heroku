class UsersController < ApplicationController
  skip_before_filter :require_login, :only => [:create]

  def index
    @user = {
        username: 'temp',
        password: 'temp',
        email: 'temp',
        firstName: 'temp',
        lastName: 'temp'
    }

    render :json => @user
  end

  def create
    @user = {
        username: params[:username],
        password: params[:password],
        email: params[:email],
        firstName: params[:firstName],
        lastName: params[:lastName]
    }

    render :json => @user
  end
end
