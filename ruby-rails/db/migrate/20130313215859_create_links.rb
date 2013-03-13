class CreateLinks < ActiveRecord::Migration
  def change
    create_table :links do |t|
      t.references :link_group, :null => false
      t.string :title, :null => false
      t.string :href, :null => false
      t.string :description

      t.timestamps
    end
  end
end
