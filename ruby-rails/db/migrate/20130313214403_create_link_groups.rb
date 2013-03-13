class CreateLinkGroups < ActiveRecord::Migration
  def change
    create_table :link_groups do |t|
      t.references :user_account, :null => false
      t.string :title, :null => false
      t.string :description

      t.timestamps
    end
  end
end
