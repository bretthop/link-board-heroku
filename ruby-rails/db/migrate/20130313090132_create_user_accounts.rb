class CreateUserAccounts < ActiveRecord::Migration
  def change
    create_table :user_accounts do |t|
      t.string :username, :null => false
      t.string :password, :null => false
      t.string :email
      t.string :first_name
      t.string :last_name

      t.timestamps
    end
  end
end
