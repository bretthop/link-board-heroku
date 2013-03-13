class ApplicationController < ActionController::Base
  before_filter :require_login

  private

  def require_login
    auth_token = request.headers['Authorization'].split('Basic ')[1]
    auth_tokens_decoded = auth_token.unpack('m')[0].split(':')

    username = auth_tokens_decoded[0]
    password = auth_tokens_decoded[1]

    @current_user = UserAccount.find_by_username username

    unless @current_user && password == @current_user.password
      response.status = 400

      render :text => 'Invalid login'
    end
  end
end
