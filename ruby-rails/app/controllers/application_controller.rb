class ApplicationController < ActionController::Base
  before_filter :require_login

  private

  def require_login
    @_temp_username = 'temp'
    @_temp_password = 'temp'

    @auth_token = request.headers['Authorization'].split('Basic ')[1]
    @auth_tokens_decoded = @auth_token.unpack('m')[0].split(':')

    @username = @auth_tokens_decoded[0]
    @password = @auth_tokens_decoded[1]

    unless @username == @_temp_username
      unless @password == @_temp_password
        response.status = 400

        render :text => 'Invalid login'
      end
    end
  end
end
