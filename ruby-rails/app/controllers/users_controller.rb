class UsersController < ApplicationController
  include UsersHelper

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

    validation_err = validate attrs

    if validation_err
      response.status = 400

      render :text => validation_err
    else
      new_user = UserAccount.create attrs

      render :json => new_user
    end
  end
end